package com.lopo.controller;


import com.lopo.domain.po.User;
import com.lopo.domain.vo.UserVO;
import com.lopo.response.R;
import com.lopo.search.domain.Product;
import com.lopo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public void setElasticsearchOperations(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('user:select')")
    public R userDetail(@PathVariable("userId") String userId){
        User user = userService.findUserById(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return R.success().setData(userVO);
    }

    @GetMapping("/test")
//    @PreAuthorize("hasAnyAuthority('user:test')")
    public String userTest(){
            Product product = new Product();
            product.setId(1);
            product.setName("iphone14 pro max");
            product.setPrice(5999.88);
            product.setDescription("美国有苹果，中国有菠萝");
            elasticsearchOperations.save(product);
        return "用于测试访问权限不足的接口";
    }
}
