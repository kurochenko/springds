package net.kurochenko.springds.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Example status controller. To check whether spring container is working
 *
 * @author kurochenko
 */
@Controller
@RequestMapping("/")
public class StatusController {

    @ResponseBody
    @RequestMapping
    public String getStatus() {
        return "OK";
    }

}
