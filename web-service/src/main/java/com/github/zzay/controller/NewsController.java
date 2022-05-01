package com.github.zzay.controller;

import com.github.zzay.entity.News;
import com.github.zzay.entity.dto.NewsDto;
import com.github.zzay.exception.SystemException;
import com.github.zzay.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzay
 * @className NewsController
 * @description News Controller
 * @create 2022/05/01 14:12
 */
@Controller
@RequestMapping("/news")
@Tag(name = "NewsController", description = "新闻信息操作相关接口")
public class NewsController {

    /**
     * News Service
     */
    @Autowired
    NewsService newsService;

    /**
     * Management page
     *
     * @param model Model
     * @param page  Page number
     * @return Management page
     */
    @GetMapping("/management")
    @PreAuthorize("hasAuthority('system:admin')")
    @Operation(summary = "管理页面跳转", description = "管理页面跳转", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String managementPage(@Parameter(name = "model") Model model,
                                 @Parameter(name = "page") @RequestParam(value = "page", required = false) Integer page) {
        if (page == null) {
            model.addAttribute("list", newsService.getListByPage(1));
            model.addAttribute("pageNum", 1);
        } else {
            model.addAttribute("list", newsService.getListByPage(page));
            model.addAttribute("pageNum", page);
        }
        model.addAttribute("page", newsService.getPublishedPageCount());
        return "management";
    }

    /**
     * Create page
     *
     * @param model Model
     * @return Create page
     */
    @GetMapping("/create")
    @Operation(summary = "创建新闻页面跳转", description = "创建新闻页面跳转", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String createPage(@Parameter(name = "model") Model model) {
        return "create";
    }

    /**
     * Edit page
     *
     * @param model Model
     * @param id    ID
     * @return Edit page
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('system:admin')")
    @Operation(summary = "编辑新闻页面跳转", description = "编辑新闻页面跳转", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String editPage(@Parameter(name = "model") Model model,
                           @Parameter(name = "id") @PathVariable Integer id) {
        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "edit";
    }

    /**
     * Audit page
     *
     * @param model Model
     * @param page  Page number
     * @return Audit page
     */
    @GetMapping("/audit")
    @PreAuthorize("hasAuthority('system:admin')")
    @Operation(summary = "审核新闻页面跳转", description = "审核新闻页面跳转", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String auditPage(@Parameter(name = "model") Model model,
                            @Parameter(name = "page") @RequestParam(value = "page", required = false) Integer page) {
        // if not given a page number, go to the first page by default
        if (page == null) {
            model.addAttribute("list", newsService.getUnpublishedList(1));
            model.addAttribute("pageNum", 1);
        } else {
            model.addAttribute("list", newsService.getListByPage(page));
            model.addAttribute("pageNum", page);
        }
        model.addAttribute("page", newsService.getUnpublishedPageCount());
        return "audit";
    }

    /**
     * Display news on news page
     *
     * @param model Model
     * @param id    ID
     * @return News page
     * @throws SystemException
     */
    @GetMapping("/getById/{id}")
    @Operation(summary = "展示新闻页面", description = "展示新闻页面：用户通过点击对应新闻传入对应新闻的ID，展示对应的新闻页面", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String displayNewsById(@Parameter(name = "model") Model model,
                                  @Parameter(name = "id") @PathVariable Integer id) throws SystemException {
        News news = newsService.getNewsById(id);
        if (news == null) {
            throw new SystemException("Failed to access because the server is being maintained, please retry later !");
        }
        model.addAttribute("news", news);
        return "news";
    }

    /**
     * Edit news
     *
     * @param model   Model
     * @param newsDto News DTO
     * @return Management page
     * @throws SystemException
     */
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('system:admin')")
    public String editNews(@Parameter(name = "model") Model model,
                           @Parameter(name = "newsDto") NewsDto newsDto) throws SystemException {
        if (!newsService.editNews(newsDto)) {
            throw new SystemException("Failed to edit because the server is being maintained, please retry later !");
        }
        model.addAttribute("list", newsService.getListByPage(1));
        model.addAttribute("pageNum", 1);
        model.addAttribute("page", newsService.getPublishedPageCount());
        return "management";
    }

    /**
     * Audit news
     *
     * @param model Model
     * @param id    ID
     * @return Audit page
     * @throws SystemException
     */
    @PreAuthorize("hasAuthority('system:admin')")
    @GetMapping("/audit/{id}")
    @Operation(summary = "审核新闻", description = "审核新闻：用户通过点击审核新闻页面，审核对应的新闻", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String auditNews(@Parameter(name = "model") Model model,
                              @Parameter(name = "id") @PathVariable int id) throws SystemException {
        if (!newsService.publishNews(id)) {
            throw new SystemException("Failed to audit because the server is being maintained, please retry later !");
        }
        model.addAttribute("list", newsService.getUnpublishedList(1));
        model.addAttribute("pageNum", 1);
        model.addAttribute("page", newsService.getUnpublishedPageCount());
        return "audit";
    }

    /**
     * Delete news
     *
     * @param model Model
     * @param id    ID
     * @return Management page
     * @throws SystemException
     */
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('system:admin')")
    @Operation(summary = "删除新闻", description = "删除新闻：用户通过点击删除新闻按钮，删除对应的新闻", security = @SecurityRequirement(name = HttpHeaders.AUTHORIZATION))
    public String deleteNews(@Parameter(name = "model") Model model,
                             @Parameter(name = "id") @PathVariable int id) throws SystemException {
        if (!newsService.deleteNews(id)) {
            throw new SystemException("Failed to delete because the server is being maintained, please retry later !");
        }
        // after deletion, jump to the first page
        model.addAttribute("list", newsService.getListByPage(1));
        model.addAttribute("pageNum", 1);
        model.addAttribute("page", newsService.getPublishedPageCount());
        return "management";
    }


}
