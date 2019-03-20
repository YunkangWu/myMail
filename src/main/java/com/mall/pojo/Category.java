package com.mall.pojo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {
    private Integer id;

    private Integer parentId;

    private String name;

    private Boolean status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

    /**
     * 重写equals方法
     *
     * @param
     * @return
     */
    //@Override
    //public boolean equals(Object o) {
    //    if (this == o) return true;
    //    if (o == null || getClass() != o.getClass()) return false;
    //    Category category = (Category) o;
    //    return Objects.equals(id, category.id);
    //}

    //@Override
    //public int hashCode() {
    //    return Objects.hash(id);
    //}
}