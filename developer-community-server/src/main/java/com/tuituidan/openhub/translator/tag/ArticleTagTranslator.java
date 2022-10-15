package com.tuituidan.openhub.translator.tag;

import com.tuituidan.tresdin.datatranslate.bean.TranslationParameter;
import com.tuituidan.tresdin.datatranslate.translator.ITranslator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * UserAvatarTranslator.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/7
 */
@Component
@Slf4j
public class ArticleTagTranslator implements ITranslator<ArticleTag> {

    @Override
    public String translate(TranslationParameter translationParameter) {
        Object fieldValue = translationParameter.getFieldValue();
        if (fieldValue == null) {
            return "";
        }
        return "";
    }

}
