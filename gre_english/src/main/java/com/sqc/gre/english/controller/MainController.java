package com.sqc.gre.english.controller;

import com.sqc.gre.english.JsonMapper;
import com.sqc.gre.english.YouDaoApi;
import com.sqc.gre.english.YouDaoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import static com.sqc.gre.english.StringArrayUtil.devideStringToArray;
import static com.sqc.gre.english.StringArrayUtil.disruptOrder;
import static com.sqc.gre.english.WordHandler.readToString;

/**
 * @author < a href="sqc95111@163.com">sqc</ a>
 * @version 1.0
 * @ClassName MainController
 * @Description TODO
 * @date 2019/4/15 12:02
 **/
@Controller
public class MainController {

    @ResponseBody
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }



    @GetMapping("homePage")
    public String homePage(){
        System.out.println("q11111111111111111111111");
        return "web.html";
    }




    @ResponseBody
    @PostMapping("wordHome")
    public String[] mainPage() {
        System.out.println("1222222222222222222");
        String words = readToString("C:\\Users\\sqc95\\Desktop\\English\\words.txt");
        String[] array = devideStringToArray(words);
        array = disruptOrder(array);
        return array;
    }

    @ResponseBody
    @PostMapping("translate")
    public String[] translatePage() throws IOException {
        System.out.println("133333333333333");
        String words = readToString("C:\\Users\\sqc95\\Desktop\\English\\words.txt");
        String[] array = devideStringToArray(words);
        array = disruptOrder(array);
        for(int i=0;i<array.length;i++){
            try {
                String response = YouDaoApi.mainMethod(array[i]);
                YouDaoResult pack = JsonMapper.nonDefaultMapper().fromJson(response, YouDaoResult.class);
                array[i] = pack.getBasic().getExplains().toString();
            }catch (Exception e){
                System.out.println(i);
            }
        }
        return array;
    }
}
