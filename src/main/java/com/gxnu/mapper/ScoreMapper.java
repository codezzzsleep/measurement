package com.gxnu.mapper;

import com.gxnu.domain.Score;
import org.apache.ibatis.annotations.Param;

public interface ScoreMapper {

    Integer insertIntoScoreAccount(@Param("account") String account);
    Integer updateScore(@Param("account") String account,@Param("ideologicalAndMoral") Double ideologicalAndMoral, @Param("courseLearning") Double courseLearning,@Param("bodyArtIntegrated") Double bodyArtIntegrated,@Param("practiceAbility") Double practiceAbility,@Param("totalScore") Double totalScore);
    Integer updateIdeologicalAndMoralScore(@Param("account") String account, @Param("score") Double score);
    Integer updateCourseLearningScore(@Param("account") String account, @Param("score") Double score);
    Integer updateBodyArtIntegratedScore(@Param("account") String account, @Param("score") Double score);
    Integer updatePracticeAbilityScore(@Param("account") String account, @Param("score") Double score);
    Integer updateTotalScoreScore(@Param("account") String account, @Param("score") Double score);
    Double  selectIdeologicalAndMoralScore(@Param("account") String account);
    Double  selectCourseLearningScore(@Param("account") String account);
    Double  selectBodyArtIntegratedScore(@Param("account") String account);
    Double  selectPracticeAbilityScore(@Param("account") String account);
    Double  selectTotalScoreScore(@Param("account") String account);
    Score   selectScore(@Param("account") String account);
}
