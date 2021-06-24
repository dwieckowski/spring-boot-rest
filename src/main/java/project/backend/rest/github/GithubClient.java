package project.backend.rest.github;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GithubClient {

    private final RestTemplate restTemplate;

    public GithubUser getUserByLogin(String login){
        ResponseEntity<GithubUser> response = restTemplate.getForEntity("https://api.github.com/users/" + login, GithubUser.class);
        return response.getBody();
    }
}
