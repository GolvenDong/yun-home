package com.golven.Controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.golven.mapper.DistrictMapper;
import com.golven.mapper.HouseMapper;
import com.golven.mapper.TypeMapper;
import com.golven.mapper.UsersMapper;
import com.golven.pojo.District;
import com.golven.pojo.House;
import com.golven.pojo.HouseType;
import com.golven.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private TypeMapper typeMapper;
    @RequestMapping("/show")
    public String show(Model model,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "3") Integer pageSize){
        //1.分页
        PageHelper.startPage(pageNo,pageSize);
        //2.查询
        List<House> houses = houseMapper.selectAll();
        System.out.println(houses);
        //3.封装pageInfo
        PageInfo<House> houseInfo = (PageInfo<House>) new PageInfo<>(houses);
        //4.存model
        model.addAttribute("houseInfo",houseInfo);
        //5.跳转showHouse.html
        return "admin/house/showHouse";
    }
    @RequestMapping("/addPage")
    public String addPage(Model model){
        //查询所有房东信息
        List<Users> landord = usersMapper.selectLandord();
        //查询区域信息
        List<District> disByParentId = districtMapper.getDisByParentId(1);
        List<HouseType> houseTypes = typeMapper.selectAll();
        //将上述结果集存入model
        model.addAttribute("users",landord);
        model.addAttribute("districts",disByParentId);
        model.addAttribute("housetype",houseTypes);
        return "admin/house/addHouse";
    }
    @RequestMapping("/streets")
    @ResponseBody//异步请求，不做页面跳转
    public List<District> streets(Integer did){
         return districtMapper.getDisByParentId(did);
    }
    @RequestMapping("/add")
    //形参house表示一次性接收整个form标签中所有的属性值，并将它封装在House对象中
    public String add(House house){
        System.out.println(house);
        houseMapper.addHouse(house);
        return "admin/admin";
    }
    //根据hid查找房源信息
    @RequestMapping("/findByHid")
    public String findByHid(@RequestParam(defaultValue = "1") Integer hid,Model model){
        System.out.println(hid);
        House house = houseMapper.selectById(hid);
        //获取房东名
        Users users = usersMapper.selectById(house.getUser_id());
        //获取街道名称
        District streetBydid = districtMapper.getStreetBydid(house.getDistrict_id());
        //获取区名
        District countyName = districtMapper.getStreetBydid(streetBydid.getParentId());
        //获取房屋类型
        HouseType houseType = typeMapper.selectById(house.getTypeid());
        model.addAttribute("user",users);
        model.addAttribute("streetBydid",streetBydid);
        model.addAttribute("countyName",countyName);
        model.addAttribute("houseType",houseType);
        model.addAttribute("house",house);
        //查询所有房东的信息
        List<Users> landords = usersMapper.selectLandord();
        //查询区域信息
        List<District> disByParentIds = districtMapper.getDisByParentId(1);
        List<HouseType> hTypes = typeMapper.selectAll();
        //将上述结果集存入model
        model.addAttribute("landords",landords);
        System.out.println("landords"+landords);
        model.addAttribute("disByParentIds",disByParentIds);
        model.addAttribute("hTypes",hTypes);
        System.out.println(model);
        return "admin/house/updateHouse";
    }
    @RequestMapping("/update")
    public String update(House house){
        System.out.println(house);
        houseMapper.updateHouse(house);
        return "admin/admin";
    }

}
