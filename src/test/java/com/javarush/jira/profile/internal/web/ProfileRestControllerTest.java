package com.javarush.jira.profile.internal.web;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.common.util.JsonUtil;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.profile.internal.ProfileRepository;
import com.javarush.jira.profile.internal.model.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.javarush.jira.login.internal.web.UserTestData.USER_ID;
import static com.javarush.jira.login.internal.web.UserTestData.USER_MAIL;
import static com.javarush.jira.profile.internal.web.ProfileRestController.REST_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileRestControllerTest extends AbstractControllerTest {
    @Autowired
    ProfileRepository profileRepository;

    @Test
    void userNoOk() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void userIsOk() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void wrongUpdate() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(ProfileTestData.getInvalidTo())))
                .andExpect(status().is4xxClientError());
    }




    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateWithUnknownContactTo() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(ProfileTestData.getWithUnknownContactTo())))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateProfile() throws Exception {
        ProfileTo updatedProfileTo = ProfileTestData.getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedProfileTo)))
                .andExpect(status().isNoContent());
        Profile updatedProfile = profileRepository.getExisted(USER_ID);
        Profile expectedProfile = ProfileTestData.getUpdated(USER_ID);
        ProfileTestData.PROFILE_MATCHER.assertMatch(updatedProfile, expectedProfile);
    }


}

