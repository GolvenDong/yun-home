package com.golven.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.golven.mapper.UsersMapper;
import com.golven.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RequestMapping("/users")
@Controller
public class UsersController {
    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping("/show")
    public String show(Model model,
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize){
        //开始分页
        PageHelper.startPage(pageNo,pageSize);
        //查询所有数据
        List<Users> users = usersMapper.selectAll();
        //封装到pageInfo中
        PageInfo<Users> usersPageInfo = new PageInfo<>(users);
        model.addAttribute("usersPageInfo",usersPageInfo);
        return "admin/users/showUsers";
    }
    //姓名的模糊查询
    @ResponseBody
    @RequestMapping("/abstract")
    public List<Users> abstractSearch(Model model,@RequestParam(defaultValue = "") String abContent){
//
//        Example example=new Example(Users.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andLike("name","%"+abContent+"%");
//        List<Users> usersList = this.usersMapper.selectByExample(example);
        List<Users> users = usersMapper.selectAbstract("%"+abContent+"%");
        System.out.println(users);
        return users;
    }
}
