package com.camellibby.account;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Slf4j
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     * @return
     */
    @GetMapping("decrease")
    public String decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money) {
        accountService.decrease(userId, money);
        return "Account decrease success";
    }
}
