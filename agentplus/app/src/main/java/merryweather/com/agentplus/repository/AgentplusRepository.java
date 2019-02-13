package merryweather.com.agentplus.repository;

import io.reactivex.subjects.BehaviorSubject;

public class AgentplusRepository {

    private final static int MAX = 100;

    private int value = 1;

    private boolean paused = false;

    public BehaviorSubject<String> roman = BehaviorSubject.create();

    public void tick() {
        if (!paused) {
            value++;
            if (value > MAX)
                value = 1;
            roman.onNext(getRoman());
        }
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    private String getRoman() {
        return RomanNumber.toRoman(value);
    }

}
