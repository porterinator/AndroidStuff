package merryweather.com.ltech.api;

import java.util.ArrayList;

import io.reactivex.Single;
import merryweather.com.ltech.model.Entity;
import merryweather.com.ltech.model.LoginResult;
import merryweather.com.ltech.model.PhoneMask;

/**
 * Created by S on 17.05.2018.
 */

public class LTechService {

    private LTechApi mApi;

    public LTechService(LTechApi mApi) {

        this.mApi = mApi;
    }

    public Single<PhoneMask> getPhoneMask() {

        return mApi.getPhoneMask().map(phoneMask -> {
            phoneMask.phoneMask = (phoneMask.phoneMask
                    .replace(" ", "] [")
                    .replace("-", "] [")
                    .replace("Х", "0") + "]")
                    .replaceFirst("]", "");
            return phoneMask;
        });
    }

    public Single<ArrayList<Entity>> getList() {
        return mApi.getList();
    }

    public Single<LoginResult> auth(String login, String password) {
        return mApi.auth(login.replace(" ", ""), password);
    }
}
