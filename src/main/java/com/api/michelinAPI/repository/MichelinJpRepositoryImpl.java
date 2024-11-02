package com.api.michelinAPI.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.michelinAPI.dto.paramDTO;
import com.api.michelinAPI.entity.MichelinJpEntity;
import com.api.michelinAPI.entity.QMichelinJpEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MichelinJpRepositoryImpl implements MichelinJpRepository{
    
    // QueryDSL
    private final JPAQueryFactory queryFactory;
    
    MichelinJpRepositoryImpl(JPAQueryFactory queryFactory){   
        this.queryFactory = queryFactory;
    }

    // QMichelinKrEntity 등록
    QMichelinJpEntity michelinJpEntity = QMichelinJpEntity.michelinJpEntity;
    
    @Override
    public List<MichelinJpEntity> findMichelinList(paramDTO dto){
        return queryFactory.from(michelinJpEntity) 
                            .select(michelinJpEntity)
                            .where(
                                equalStarCnt(dto.getStarCnt())
                                , equalFcltyNm(dto.getFcltyNm())
                                , equalCtprvnEngNm(dto.getCtprvnEngNm())
                            )
                            .limit(dto.getRow())
                            .fetch(); 
    }

    // 별개수 체크 메서드
    private BooleanExpression equalStarCnt(Integer starCnt){
        return starCnt != null ? michelinJpEntity.starCnt.eq(starCnt) : null;
    } 

    // 레스토랑이름 체크 메서드
    private BooleanExpression equalFcltyNm(String fcltyNm){
        return fcltyNm != null ? michelinJpEntity.fcltyNm.eq(fcltyNm) : null;
    } 

    // 시도영문명 체크 메서드
    private BooleanExpression equalCtprvnEngNm(String ctprvnEngNm){
        return ctprvnEngNm != null ? michelinJpEntity.ctprvnEngNm.eq(ctprvnEngNm) : null;
    } 
}
