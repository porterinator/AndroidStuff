package merryweather.com.easymarkets.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import merryweather.com.easymarkets.model.Appartment;

public class AppartmentRepository {

    public BehaviorSubject<List<Appartment>> appartments = BehaviorSubject.create();
    public BehaviorSubject<List<Appartment>> filtered = BehaviorSubject.create();

    public AppartmentRepository () {
        // set correct dates for demonstration
        ArrayList<Appartment> list = new ArrayList<>();
        list.add(new Appartment(1 ,new Date(), new Date()));
        list.add(new Appartment(1 ,new Date(), new Date()));
        list.add(new Appartment(1 ,new Date(), new Date()));
        list.add(new Appartment(1 ,new Date(), new Date()));

        list.add(new Appartment(2 ,new Date(), new Date()));
        list.add(new Appartment(2 ,new Date(), new Date()));
        list.add(new Appartment(2 ,new Date(), new Date()));

        list.add(new Appartment(3 ,new Date(), new Date()));
        list.add(new Appartment(3 ,new Date(), new Date()));
        list.add(new Appartment(3 ,new Date(), new Date()));
        appartments.onNext(list);
    }

    public void search(int bedrtoomCount, Date avStart, Date avEnd) {
        appartments
                .flatMap(
                        appartments1 -> Observable.fromIterable(appartments1)
                                .filter(appartment ->
                                        appartment.numberOfBedrooms == bedrtoomCount
                                        && (appartment.availableStart.after(avStart) && appartment.availableStart.before(avEnd))
                                                && (appartment.availableEnd.after(avStart) && appartment.availableEnd.before(avEnd))
                                )
                                .toList()
                                .toObservable()
                )

                /*.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())*/
                .subscribe((appartments1) -> {
                    filtered.onNext(appartments1);
                });
    }

    public void book(Appartment appartment) {
        appartments.getValue().remove(appartment);
        filtered.getValue().remove(appartment);
        filtered.onNext(filtered.getValue());
    }

}
