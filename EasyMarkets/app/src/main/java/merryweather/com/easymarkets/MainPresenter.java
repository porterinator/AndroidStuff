package merryweather.com.easymarkets;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Date;

import javax.inject.Inject;

import merryweather.com.easymarkets.model.Appartment;
import merryweather.com.easymarkets.repository.AppartmentRepository;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private AppartmentRepository mAppartmentRepository;


    @Inject
    public MainPresenter(AppartmentRepository appartmentRepository) {
        mAppartmentRepository = appartmentRepository;

    }

    @Override
    protected void onFirstViewAttach() {
        mAppartmentRepository.filtered.subscribe(appartments -> {
            getViewState().showAppartments(appartments);
        });
    }

    public void search(int bedroomCount, Date avStart, Date avEnd) {
        mAppartmentRepository.search(bedroomCount, avStart, avEnd);
    }

    public void book(Appartment app) {
        mAppartmentRepository.book(app);
    }
}
