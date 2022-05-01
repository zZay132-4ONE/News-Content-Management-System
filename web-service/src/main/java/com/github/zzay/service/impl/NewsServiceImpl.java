package com.github.zzay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zzay.entity.News;
import com.github.zzay.entity.dto.NewsDto;
import com.github.zzay.enums.NewsEnums;
import com.github.zzay.enums.PageEnums;
import com.github.zzay.mapper.NewsMapper;
import com.github.zzay.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzay
 * @className NewsServiceImpl
 * @description News Service Implementation
 * @create 2022/05/01 12:56
 */
@Slf4j
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    /**
     * News Mapper
     */
    @Autowired
    private NewsMapper newsMapper;

    /**
     * Get a news with the given ID
     *
     * @param id News ID
     * @return A news with the given ID
     */
    @Override
    public News getNewsById(Integer id) {
        return this.getById(id);
    }

    /**
     * Get total count of published news
     *
     * @return Total count of published news
     */
    @Override
    public Integer getPublishedNewsCount() {
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>().eq("published", NewsEnums.PUBLISHED.getValue());
        int newsCount = this.count(queryWrapper);
        log.info("======== Count of published news: " + newsCount + " ========");
        return newsCount;
    }

    /**
     * Get total count of unpublished news
     *
     * @return Total count of unpublished news
     */
    @Override
    public Integer getUnpublishedNewsCount() {
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>().eq("published", NewsEnums.UNPUBLISHED.getValue());
        int newsCount = this.count(queryWrapper);
        log.info("======== Count of unpublished news: " + newsCount + " ========");
        return newsCount;
    }

    /**
     * Get total count of published pages
     *
     * @return Total count of published pages
     */
    @Override
    public Integer getPublishedPageCount() {
        Integer newsCount = this.getPublishedNewsCount();
        log.info("======== Count of pages with published news: " + newsCount + " ========");
        return (newsCount % PageEnums.RECORDS_PER_PAGE.getValue() == 0) ? (newsCount / 5) : (newsCount / 5 + 1);
    }

    /**
     * Get total count of unpublished pages
     *
     * @return Total count of unpublished pages
     */
    @Override
    public Integer getUnpublishedPageCount() {
        Integer newsCount = this.getUnpublishedNewsCount();
        log.info("======== Count of pages with unpublished news: " + newsCount + " ========");
        return (newsCount % PageEnums.RECORDS_PER_PAGE.getValue() == 0) ? (newsCount / 5) : (newsCount / 5 + 1);
    }

    /**
     * Get the published news list on the given page
     *
     * @param page The given page number
     * @return The published news list on the given page
     */
    @Override
    public List<News> getListByPage(Integer page) {
        // Select news that are published
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>().select("id", "title", "description")
                .eq("published", NewsEnums.PUBLISHED.getValue());
        // Get the page with published news with the given page number
        Page<News> newsPage = new Page<>(page, PageEnums.RECORDS_PER_PAGE.getValue());
        // Get published news on the given page
        IPage<News> iPage = page(newsPage, queryWrapper);
        return iPage.getRecords();
    }

    /**
     * Get the unpublished news list on the given page
     *
     * @param page The given page number
     * @return The unpublished news list on the given page
     */
    @Override
    public List<News> getUnpublishedList(Integer page) {
        // Select news that are unpublished
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>().select("id", "title", "description")
                .eq("published", NewsEnums.UNPUBLISHED.getValue());
        // Get the page with unpublished news with the given page number
        Page<News> newsPage = new Page<>(page, PageEnums.RECORDS_PER_PAGE.getValue());
        // Get unpublished news on the given page
        IPage<News> iPage = page(newsPage, queryWrapper);
        return iPage.getRecords();
    }

    /**
     * Add a news
     *
     * @param newsDto News DTO
     * @return Operation result
     */
    @Override
    public Boolean addNews(NewsDto newsDto) {
        News news = new News();
        news.setTitle(newsDto.getTitle());
        news.setDescription(newsDto.getDescription());
        news.setContents(newsDto.getContents());
        return this.save(news);
    }

    /**
     * Publish a news
     *
     * @param id News ID
     * @return Operation result
     */
    @Override
    public Boolean publishNews(Integer id) {
        UpdateWrapper<News> updateWrapper = new UpdateWrapper<News>()
                .set("published", NewsEnums.PUBLISHED.getValue()).eq("id", id);
        return this.update(updateWrapper);
    }

    /**
     * Edit a news
     *
     * @param newsDto News DTO
     * @return Operation result
     */
    @Override
    public Boolean editNews(NewsDto newsDto) {
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setDescription(newsDto.getDescription());
        news.setContents(newsDto.getContents());
        news.setPublished(NewsEnums.PUBLISHED.getValue());
        return this.updateById(news);
    }

    /**
     * Delete a news
     *
     * @param id News ID
     * @return Operation result
     */
    @Override
    public Boolean deleteNews(Integer id) {
        return this.removeById(id);
    }

}
