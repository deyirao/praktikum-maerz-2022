package de.hhu.chicken.infrastructure.web.configuration;

import static de.hhu.chicken.infrastructure.web.configuration.AuthenticationTemplates.organisatorSession;
import static de.hhu.chicken.infrastructure.web.configuration.AuthenticationTemplates.studentSession;
import static de.hhu.chicken.infrastructure.web.configuration.AuthenticationTemplates.tutorSession;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.hhu.chicken.service.repositories.KlausurRepository;
import de.hhu.chicken.service.repositories.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WebSecurityConfigurationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private KlausurRepository klausurRepository;

  @MockBean
  private StudentRepository studentRepository;

  @Test
  @DisplayName("Uneingeloggter Nutzer wird auf GitHub redirected")
  void test_1() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().is3xxRedirection());
  }

  @Test
  @DisplayName("Studenten duerfen auf die Studentenseite zugreifen")
  void test_2() throws Exception {
    mockMvc.perform(get("/").session(studentSession()))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Studenten duerfen nicht auf die Tutorenseite zugreifen")
  void test_3() throws Exception {
    mockMvc.perform(get("/tutor").session(studentSession()))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("Studenten duerfen nicht auf die Organisatorenseite zugreifen")
  void test_4() throws Exception {
    mockMvc.perform(get("/organisator").session(studentSession()))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("Tutoren duerfen nicht auf die Studentenseite zugreifen")
  void test_5() throws Exception {
    mockMvc.perform(get("/").session(tutorSession()))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("Tutoren duerfen auf die Tutorenseite zugreifen")
  void test_6() throws Exception {
    mockMvc.perform(get("/tutor").session(tutorSession()))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Tutoren duerfen nicht auf die Organisatorenseite zugreifen")
  void test_7() throws Exception {
    mockMvc.perform(get("/organisator").session(tutorSession()))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("Organisatoren duerfen nicht auf die Studentenseite zugreifen")
  void test_8() throws Exception {
    mockMvc.perform(get("/").session(organisatorSession()))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("Organisatoren duerfen nicht auf die Tutorenseite zugreifen")
  void test_9() throws Exception {
    mockMvc.perform(get("/tutor").session(organisatorSession()))
        .andExpect(status().isForbidden());
  }

  @Test
  @DisplayName("Organisatoren duerfen auf die Organisatorenseite zugreifen")
  void test_10() throws Exception {
    mockMvc.perform(get("/organisator").session(organisatorSession()))
        .andExpect(status().isOk());
  }
}