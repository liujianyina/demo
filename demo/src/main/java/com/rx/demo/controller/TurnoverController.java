package com.rx.demo.controller;

import com.rx.demo.domain.Turnover;
import com.rx.demo.service.TurnoverService;
import com.rx.demo.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/turnover")
public class TurnoverController extends BaseController {

    @Autowired
    private TurnoverService turnoverService;

    @GetMapping("/turnover_list")
    public String turnoverList(){
        return "/turnover/turnover_list";
    }

    @ResponseBody
    @PostMapping("/turnover_list")
    public AjaxResult turnoverList(int page,int limit){
        Page<Turnover> turnovers = turnoverService.turnoverList(page,limit);
        return AjaxResult.success(turnovers.getTotalElements(),turnovers.getContent());
    }

    @ResponseBody
    @PostMapping("/user_turnover_list")
    public AjaxResult userTurnoverList(int page,int limit,Long userSid){
        Page<Turnover> turnovers = turnoverService.turnoverList(page,limit,userSid);
        return AjaxResult.success(turnovers.getTotalElements(),turnovers.getContent());
    }

}
