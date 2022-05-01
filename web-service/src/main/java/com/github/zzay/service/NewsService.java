package com.github.zzay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zzay.entity.News;
import com.github.zzay.entity.dto.NewsDto;

import java.util.List;

/**
 * @author zzay
 * @interfaceName NewsService
 * @description News Service
 * @create 2022/05/01 12:55
 * @see com.github.zzay.service.impl.NewsServiceImpl
 */
public interface NewsService extends IService<News> {

    /**
     * Get a news with the given ID
     *
     * @param id News ID
     * @return A news with the given ID
     */
    News getNewsById(Integer id);

    /**
     * Get total count of news
     *
     * @return Total count of news
     */
    Integer getPublishedNewsCount();

    /**
     * Get total count of news
     *
     * @return Total count of news
     */
    Integer getUnpublishedNewsCount();

    /**
     * Get total count of unpublished pages
     *
     * @return Total count of unpublished pages
     */
    Integer getPublishedPageCount();

    /**
     * Get total count of unpublished pages
     *
     * @return Total count of unpublished pages
     */
    Integer getUnpublishedPageCount();

    /**
     * Get the news list on the given page
     *
     * @param page The given page number
     * @return The news list on the given page
     */
    List<News> getListByPage(Integer page);

    /**
     * Get the unpublished news list on the given page
     *
     * @param page The given page number
     * @return The unpublished news list on the given page
     */
    List<News> getUnpublishedList(Integer page);

    /**
     * Add a news
     *
     * @param newsDto News DTO
     * @return Operation result
     */
    Boolean addNews(NewsDto newsDto);

    /**
     * Publish a news
     *
     * @param id News ID
     * @return Operation result
     */
    Boolean publishNews(Integer id);

    /**
     * Edit a news
     *
     * @param newsDto News DTO
     * @return Operation result
     */
    Boolean editNews(NewsDto newsDto);

    /**
     * Delete a news
     *
     * @param id News ID
     * @return Operation result
     */
    Boolean deleteNews(Integer id);

}
