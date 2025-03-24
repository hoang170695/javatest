package hoang.tran.test.service;

import hoang.tran.test.converter.PageResponseConverter;
import hoang.tran.test.exception.KeyCannotBeFoundException;
import hoang.tran.test.model.UserKey;
import hoang.tran.test.repostitory.UserKeyRepository;
import hoang.tran.test.converter.PageableConverter;
import hoang.tran.test.request.PagingOption;
import hoang.tran.test.response.PageResponse;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

@Service
public class UserKeyService {

    private final UserKeyRepository userKeyRepository;
    private final Random random = new Random();

    public UserKeyService(UserKeyRepository userKeyRepository) {
        this.userKeyRepository = userKeyRepository;
    }

    @Transactional
    public UserKey createUserKey(String username) {
        if (username == null || username.trim().isEmpty() || username.length() > 150) {
            throw new IllegalArgumentException("Invalid username");
        }

        // Check if user already has 5 keys
        if (userKeyRepository.countByUsername(username) >= 5) {
            throw new IllegalArgumentException("User already has 5 keys");
        }

        // Generate unique code
        String code;
        do {
            String rawCode = username + (random.nextInt(100) + 1);
            code = Base64.getEncoder().encodeToString(rawCode.getBytes(StandardCharsets.UTF_8));
        } while (isCodeDuplicate(username, code));

        // Save new key
        UserKey userKey = new UserKey(username, code);
        return userKeyRepository.save(userKey);
    }

    public PageResponse<UserKey> getUserKeys(String username, PagingOption pagingOption) {
        Page<UserKey> userKeyPages = userKeyRepository.findByUserKey(username, PageableConverter.convert(pagingOption));

        if(StringUtils.isNotBlank(username) && userKeyPages.get().count() == 0) {
            throw  new KeyCannotBeFoundException();
        }

        return PageResponseConverter.convert(userKeyPages);
    }

    public void deleteUserKey(Long id) {
        if (!userKeyRepository.existsById(id)) {
            throw new IllegalArgumentException("User not exist");
        }
        userKeyRepository.deleteById(id);
    }

    private boolean isCodeDuplicate(String username, String code) {
        return userKeyRepository.findByUsernameAndCode(username, code).isPresent();
    }
}
