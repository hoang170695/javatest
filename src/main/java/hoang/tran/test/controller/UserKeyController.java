package hoang.tran.test.controller;

import hoang.tran.test.model.UserKey;
import hoang.tran.test.request.PagingOption;
import hoang.tran.test.response.ApiResponse;
import hoang.tran.test.response.ApiResponseGenerator;
import hoang.tran.test.response.PageResponse;
import hoang.tran.test.service.UserKeyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keys")
public class UserKeyController {

    private final UserKeyService userKeyService;

    public UserKeyController(UserKeyService userKeyService) {
        this.userKeyService = userKeyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserKey>> createKey(@RequestParam String username) {
        UserKey userKey = userKeyService.createUserKey(username);
        ApiResponse<UserKey> apiResponse = ApiResponseGenerator.success(userKey);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<UserKey>>> getAllKeys(@RequestParam(required = false) String username,
                                                                         @ModelAttribute PagingOption pageOptions) {
        PageResponse<UserKey> userKeyPageResponse = userKeyService.getUserKeys(username, pageOptions);
        ApiResponse<PageResponse<UserKey>> apiResponse = ApiResponseGenerator.success(userKeyPageResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteKey(@PathVariable Long id) {
        userKeyService.deleteUserKey(id);
        ApiResponse<Void> apiResponse = ApiResponseGenerator.success(null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
