package com.ruoyi.web.controller.pension.user;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.entity.PeContacts;
import com.ruoyi.domain.param.user.PageQuery;
import com.ruoyi.domain.param.user.PeContactsParam;
import com.ruoyi.domain.vo.user.PeContactsQueryVO;
import com.ruoyi.service.IPeContactsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "联系人管理")
@RestController
@RequestMapping("/contacts")
public class ContactsController extends BaseController {

    @Autowired
    private IPeContactsService  contactsService;

    @ApiOperation("联系人列表")
    @GetMapping("/list")
    public TableDataInfo listContacts(
            @Validated PageQuery query,
            @ApiParam("老人id") @RequestParam(required = true) Long customerId,
            @ApiParam("是否查询紧急联系人") String isDefault
    ){
        startPage();
        List<PeContactsQueryVO> list = contactsService.list(customerId, query,isDefault);
        return getDataTable(list);
    }

    @PostMapping( "/add")
    @ApiOperation("添加联系人")
    public R addContacts(
            @RequestBody PeContactsParam PeContactsParam
                        ){
        return contactsService.addContacts(PeContactsParam);

    }

    @ApiOperation("修改联系人")
    @PutMapping("/update")
    public R updateContacts(@RequestBody PeContactsParam peContactsParam){
        return contactsService.updateContacts(peContactsParam);
    }

    @ApiOperation("删除联系人")
    @DeleteMapping("/delete/{contactsId}")
    public R deleteContacts(@PathVariable @ApiParam("联系人id") Long contactsId){
        return contactsService.deleteContacts(contactsId);
    }
}
