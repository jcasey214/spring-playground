package com.example.service;

import com.example.model.Movie;
import com.example.model.remote.OmdbResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {
    @Test
    public void fetchMoviesByQueryParam() throws JsonProcessingException {
        MovieService service = new MovieService();
        ObjectMapper objectMapper = new ObjectMapper();

        Movie movie = new Movie("title", "id", "poster", 2004, "type");
        OmdbResponse mockResults = new OmdbResponse(singletonList(movie), "666", "Yeah!");

        MockRestServiceServer server = MockRestServiceServer.createServer(service.getRestTemplate());

        server.expect(requestTo("http://www.omdbapi.com/?s=harry"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(objectMapper.writeValueAsString(mockResults),
                        MediaType.APPLICATION_JSON));

        List<Movie> searchResults = service.fetchMovies("harry");

        assertThat(searchResults.get(0).getTitle(), equalTo("title"));
        assertThat(searchResults.get(0).getYear(), equalTo(2004));
        assertThat(searchResults.get(0).getImdbId(), equalTo("id"));
        assertThat(searchResults.get(0).getPoster(), equalTo("poster"));

        server.verify();
    }
}