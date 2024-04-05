package com.cpr.abyblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aby
 * @since 2024-04-03 08:42:58
 */
@Getter
@Setter
public class Inspiration implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "inspiration_id", type = IdType.AUTO)
    private Integer inspirationId;

    private String inspirationTheme;

    private Integer inspirationLike;

    private Integer inspirationCollect;

    private String inspirationAbstract;

    private LocalDateTime inspirationUploadTime;
}
