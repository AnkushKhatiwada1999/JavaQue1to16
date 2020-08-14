package Coursework;

import java.util.Comparator;

public class Sort implements Comparator<TourGuide> {

    @Override
    public int compare(TourGuide a, TourGuide b) {
        return a.getTourGuide().compareTo(b.getTourGuide());
    }

}
