package com.socks.api;

//import com.futurecloud.enums.FilterType;
//import com.futurecloud.enums.Sort;

import java.util.*;
import java.util.stream.*;

public class QueryParams {

    public static Builder builder() {
        return new Builder();
    }
    private static HashMap<String, Object> queryParams;

    private QueryParams(HashMap<String, Object> builder) {
        queryParams = builder;
    }


    public HashMap<String, Object> getParams() {
        return queryParams;
    }

    public final static class Builder {

        private final HashMap<String, Object> query = new HashMap<>();

        public Builder addParentId(Integer parentId) {
            query.put("parentId", parentId);
            return this;
        }
//
//        public Builder addContentTypeEqFilter(FilterType filter) {
//            query.put("filter[type][eq]", filter.getType());
//            return this;
//        }
//
//        public Builder addContentTypeNonEqFilter(FilterType filter) {
//            query.put("filter[type][neq]", filter.getType());
//            return this;
//        }

        public Builder addNameFilter(String name) {
            query.put("filter[name][eq]", name);
            return this;
        }

        /**
         * @param deleteFilter 0 - not-deleted; 1 - deleted.
         * @return Builder
         */
        public Builder addIsDeletedFilter(Integer deleteFilter) {
            query.put("filter[isDeleted][eq]", deleteFilter);
            return this;
        }

        public Builder addUploadedByFilter(String filter) {
            query.put("filter[uploadedBy][eq]", filter);
            return this;
        }

//        public Builder addSort(Sort...sort) {
//            query.put("sort",
//                    StringUtils.join(Stream.of(sort)
//                            .map(Sort::getVal)
//                            .collect(toList()), ",")
//            );
//            return this;
//        }

        public Builder addPage(Integer page) {
            query.put("page", page);
            return this;
        }

        public Builder addLimit(Integer limit) {
            query.put("limit", limit);
            return this;
        }

        public Builder addStorageObjectIds(Integer... ids) {
            query.put("storageObjectIds[]",
                    Stream.of(ids)
                            .map(String::valueOf)
                            .collect(Collectors.toList())
            );
            return this;
        }

        public Builder includeStorageObjects() {
            query.put("include", "storageObjects");
            return this;
        }

        public synchronized QueryParams build() {
            return new QueryParams(this.query);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(queryParams.entrySet().toArray());
    }


//    public static void main(String[] args) {
//        QueryParams.builder().addContentTypeNonEqFilter().addContentTypeEqFilter().build();
//
//        given().header().queryParams().when()
//    }
}