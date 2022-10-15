package com.tuituidan.openhub.translator.user;

import com.tuituidan.openhub.pojo.vo.UserVO;
import com.tuituidan.openhub.service.UserService;
import com.tuituidan.tresdin.datatranslate.bean.TranslationParameter;
import com.tuituidan.tresdin.datatranslate.translator.ITranslator;
import java.util.Objects;
import javax.annotation.Resource;
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
public class UserAvatarTranslator implements ITranslator<UserAvatar> {

    @Resource
    private UserService userService;

    /**
     * getFieldName
     *
     * @param fieldName fieldName
     * @return String
     */
    @Override
    public String getFieldName(String fieldName) {
        return fieldName + "Avatar";
    }

    @Override
    public String translate(TranslationParameter translationParameter) {
        Object fieldValue = translationParameter.getFieldValue();
        if (fieldValue == null) {
            return "";
        }
        UserVO user = userService.getUser(Objects.toString(fieldValue));
        if (user == null) {
            return "";
        }
        return user.getAvatar();
    }

}
