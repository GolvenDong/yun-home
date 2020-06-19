package com.golven.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.golven.mapper.DistrictMapper;
import com.golven.mapper.HouseMapper;
import com.golven.mapper.TypeMapper;
import com.golven.pojo.District;
import com.golven.pojo.House;
import com.golven.pojo.HouseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/home")
public class FrontController {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private HouseMapper houseMapper;
    @RequestMapping("/list")
    public String homelist(Model model){
        //查询城区
        List<District> disByParentId = districtMapper.getDisByParentId(1);

        //查询房屋型号
        List<HouseType> houseTypes = typeMapper.selectAll();
        //存入model
        model.addAttribute("districts",disByParentId);
        model.addAttribute("types",houseTypes);
        System.out.println(disByParentId);
        System.out.println(houseTypes);
        return "/front/home_list";
    }
    @RequestMapping("/showHomeList")
    @ResponseBody
    public PageInfo<House> showHomeList(
            @RequestParam(defaultValue="1") Integer pageNum,
            @RequestParam(defaultValue="8") Integer pageSize,
            String district_id,String price,String area,String typeid){
        //将下面参数装入map中
        HashMap<String, String> condition = new HashMap<>();
        condition.put("district_id",district_id);
        condition.put("typeid",typeid);
        System.out.println("typeid = "+typeid);
        //拆开price和area
        if(price!=null){
            String[] arr_price = price.split("-");
            String min_price = arr_price[0];
            String max_price = arr_price[1];
            condition.put("min_price",min_price);
            condition.put("max_price",max_price);
        }
        if(area!=null){
            String[] arr_area = area.split("-");
            String min_area = arr_area[0];
            String max_area = arr_area[1];
            condition.put("min_area",min_area);
            condition.put("max_area",max_area);
        }
        //分页
        PageHelper.startPage(pageNum,pageSize);
        //查询符合条件的数据
        List<House> houses = houseMapper.selectByCondition(condition);
        System.out.println(houses);
        return new PageInfo<>(houses);
    }
}
