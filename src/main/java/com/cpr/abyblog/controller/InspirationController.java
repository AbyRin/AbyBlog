package com.cpr.abyblog.controller;

import com.cpr.abyblog.entity.Inspiration;
import com.cpr.abyblog.mapper.InspirationMapper;
import com.cpr.abyblog.service.InspirationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aby
 * @since 2024-04-03 08:42:58
 */
@RestController
@RequestMapping("/inspiration")
public class InspirationController {

    @Autowired
    private InspirationMapper inspirationMapper;

    @Autowired
    private InspirationService inspirationService;

    @GetMapping("/getInspirationList")
    public List<Inspiration> getInspirationList() {
        return inspirationMapper.getInspirationList();
    }
}
