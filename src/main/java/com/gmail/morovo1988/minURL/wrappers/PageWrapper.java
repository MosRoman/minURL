package com.gmail.morovo1988.minURL.wrappers;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageWrapper<T> {

    public static final int MAX_PAGE_ITEM_DISPLAY = 5;

    private final Page<T> page;
    private final List<PageItem> items;
    private final int currentNumber;
    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageWrapper(final Page<T> page, final String url){
        this.page = page;
        this.url = url;
        this.items = new ArrayList<>();

        this.currentNumber = page.getNumber() + 1; //start from 1 to match page.page

        int start, size;
        if (page.getTotalPages() <= PageWrapper.MAX_PAGE_ITEM_DISPLAY){
            start = 1;
            size = page.getTotalPages();
        } else {
            if (this.currentNumber <= PageWrapper.MAX_PAGE_ITEM_DISPLAY - PageWrapper.MAX_PAGE_ITEM_DISPLAY /2){
                start = 1;
                size = PageWrapper.MAX_PAGE_ITEM_DISPLAY;
            } else if (this.currentNumber >= page.getTotalPages() - PageWrapper.MAX_PAGE_ITEM_DISPLAY /2){
                start = page.getTotalPages() - PageWrapper.MAX_PAGE_ITEM_DISPLAY + 1;
                size = PageWrapper.MAX_PAGE_ITEM_DISPLAY;
            } else {
                start = this.currentNumber - PageWrapper.MAX_PAGE_ITEM_DISPLAY /2;
                size = PageWrapper.MAX_PAGE_ITEM_DISPLAY;
            }
        }

        for (int i = 0; i<size; i++){
            this.items.add(new PageWrapper.PageItem(start+i, start+i == this.currentNumber));
        }
    }

    public List<PageItem> getItems(){
        return this.items;
    }

    public int getNumber(){
        return this.currentNumber;
    }

    public List<T> getContent(){
        return this.page.getContent();
    }

    public int getSize(){
        return this.page.getSize();
    }

    public int getTotalPages(){
        return this.page.getTotalPages();
    }

    public long getTotalElements() {return this.page.getTotalElements();}

    public boolean isFirstPage(){
        return this.page.isFirst();
    }

    public boolean isLastPage(){
        return this.page.isLast();
    }

    public boolean isHasPreviousPage(){
        return this.page.hasPrevious();
    }

    public boolean isHasNextPage(){
        return this.page.hasNext();
    }

    public class PageItem {
        private final int number;
        private final boolean current;
        public PageItem(int number, boolean current){
            this.number = number;
            this.current = current;
        }

        public int getNumber(){
            return this.number;
        }

        public boolean isCurrent(){
            return this.current;
        }
    }
}
