package br.com.lucasfuck.user.adapter;

import br.com.lucasfuck.user.adapter.advisor.ControllerAdvisor;
import br.com.lucasfuck.user.core.service.UserService;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(ControllerAdvisor.class)
class UserControllerTest {

    @MockBean
    private UserService service;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ConstraintViolation<?> constraintViolation;

    @Test
    void testCreate() throws Exception {
        mockMvc.perform(post("/user")
                        .content(readDocument("/valid_user.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(service).create(any());
    }

    @Test
    void testCreateInvalidJson() throws Exception {
        ConstraintViolationException constraintViolationException = new ConstraintViolationException("message", Set.of(constraintViolation));
        when(service.create(any())).thenThrow(constraintViolationException);

        mockMvc.perform(post("/user")
                        .content(readDocument("/invalid_user.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/user/{id}", 1))
                .andExpect(status().isOk());

        verify(service).findById(1L);
    }

    @Test
    void testGetByName() throws Exception {
        mockMvc.perform(get("/user")
                        .param("name", "name"))
                .andExpect(status().isOk());

        verify(service).findAllByName("name");
    }

    @Test
    void testPatch() throws Exception {
        mockMvc.perform(patch("/user/{id}", 1)
                        .content(readDocument("/valid_user.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).update(eq(1L), any());
    }

    private String readDocument(String fileName) throws IOException {
        return IOUtils.readLines(Objects.requireNonNull(this.getClass().getResourceAsStream(fileName)),
                StandardCharsets.UTF_8).stream().map(String::trim).collect(Collectors.joining());
    }

}