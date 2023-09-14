package com.xsis.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xsis.movie.model.Movie;
import com.xsis.movie.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreate() throws Exception {
        Movie movie = new Movie();
        movie.setId(2L);
        movie.setDescription("abc");
        movie.setTitle("aaa");
        movie.setRating(7.0);
        String json = objectMapper.writeValueAsString(movie);
        mockMvc.perform(post("/api_v1/Movies").content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    public void testReadById() throws Exception {
        mockMvc.perform(get("/api_v1/Movies/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testReadList() throws Exception {
        mockMvc.perform(get("/api_v1/Movies"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        Movie movie = new Movie();
        movie.setDescription("abcx");
        movie.setTitle("aaa");
        movie.setRating(6.0);
        String json = objectMapper.writeValueAsString(movie);
        mockMvc.perform(patch("/api_v1/Movies/2").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api_v1/Movies/2"))
                .andExpect(status().isNoContent());
    }

}
