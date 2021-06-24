package project.backend.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.rest.dto.User;
import project.backend.rest.entity.ApiUsage;
import project.backend.rest.github.GithubClient;
import project.backend.rest.github.GithubUser;
import project.backend.rest.repository.ApiUsageRepository;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class UserService {

    private final GithubClient githubClient;
    private final ApiUsageRepository apiUsageRepository;

    public User getUserByLogin(String login) {
        saveApiUsageInfo(login);

        GithubUser githubUser = githubClient.getUserByLogin(login);
        return User.builder()
                .id(String.valueOf(githubUser.getId()))
                .login(githubUser.getLogin())
                .name(githubUser.getName())
                .type(githubUser.getType())
                .avatarUrl(githubUser.getAvatarUrl())
                .createdAt(githubUser.getCreatedAt())
                .calculations(calculate(githubUser))
                .build();
    }

    private void saveApiUsageInfo(String login) {
        apiUsageRepository.findById(login)
                .ifPresentOrElse(
                        increaseRequestCount(),
                        setRequestCountToOne(login));
    }

    private Runnable setRequestCountToOne(String login) {
        return () -> apiUsageRepository.save(new ApiUsage(login, 1L));
    }

    private Consumer<ApiUsage> increaseRequestCount() {
        return apiUsage -> {
            apiUsage.setRequestCount(apiUsage.getRequestCount() + 1L);
            apiUsageRepository.save(apiUsage);
        };
    }

    private Double calculate(GithubUser githubUser) {
        return 6.0 / githubUser.getFollowers() * (2 + githubUser.getPublicRepos());
    }
}
