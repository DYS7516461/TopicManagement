package com.ruoyi.web.controller.topic;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.TopicTitle;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ITopicTitleService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 题目Controller
 *
 * @author HY
 * @date 2023-02-22
 */
@RestController
@RequestMapping("/topic/title")
public class TopicTitleController extends BaseController
{
    @Autowired
    private ITopicTitleService topicTitleService;

    /**
     * 查询题目列表
     */
    @PreAuthorize("@ss.hasPermi('system:topic:list')")
    @GetMapping("/list")
    public TableDataInfo list(TopicTitle topicTitle)
    {
        startPage();
        List<TopicTitle> list = topicTitleService.selectTopicTitleList(topicTitle);
        return getDataTable(list);
    }

    /**
     * 导出题目列表
     */
    @PreAuthorize("@ss.hasPermi('system:topic:export')")
    @Log(title = "题目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TopicTitle topicTitle)
    {
        List<TopicTitle> list = topicTitleService.selectTopicTitleList(topicTitle);
        ExcelUtil<TopicTitle> util = new ExcelUtil<TopicTitle>(TopicTitle.class);
        util.exportExcel(response, list, "题目数据");
    }

    /**
     * 获取题目详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:topic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(topicTitleService.selectTopicTitleById(id));
    }

    /**
     * 新增题目
     */
    @PreAuthorize("@ss.hasPermi('system:topic:add')")
    @Log(title = "题目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TopicTitle topicTitle)
    {
        return toAjax(topicTitleService.insertTopicTitle(topicTitle));
    }

    /**
     * 修改题目
     */
    @PreAuthorize("@ss.hasPermi('system:topic:edit')")
    @Log(title = "题目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TopicTitle topicTitle)
    {
        return toAjax(topicTitleService.updateTopicTitle(topicTitle));
    }

    /**
     * 删除题目
     */
    @PreAuthorize("@ss.hasPermi('system:topic:remove')")
    @Log(title = "题目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(topicTitleService.deleteTopicTitleByIds(ids));
    }
}
