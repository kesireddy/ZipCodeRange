package com.bharath.exercise.zipcode;

import java.util.ArrayList;
import java.util.List;

public class ZipCodeUtils {
    /**
     * Takes a zipcode ranges list as input and returns a smaller or equal number
     * of zipcode ranges that cover all the zipcodes specified by input
     * @param input input ranges
     * @return compressed ranges
     */
    public List<Range> getCompressedRange(final List<Range> input) {
        input.parallelStream().forEach(this::validateRanges);
        input.sort((o1, o2) -> o1.getStart()-o2.getStart());
        List<Range> processedRanges = new ArrayList<>();
        for(int i=0; i < input.size(); i++) {
            int start = input.get(i).getStart();
            int end = input.get(i).getEnd();
            for(int j=i+1; j < input.size(); j++) {
                int nextStart = input.get(j).getStart();
                int nextEnd = input.get(j).getEnd();
                if(nextStart <= end+1) {
                    end = end > nextEnd ? end: nextEnd;
                    i++;
                } else {
                    break;
                }
            }
            processedRanges.add(new Range(start, end));
        }
        return processedRanges;
    }

    /**
     * Validates the range and returns true if end > start and both are positive
     * If not a valid zip code range it throws an exception
     * @param range range object to be validated
     * @return true if valid
     */
    private boolean validateRanges(final Range range) {
        if(range.getStart() < 0 && range.getEnd() < 0) {
            throw new RangeException("Zip code must be positive" + range);
        }
        if(range.getEnd() < range.getStart()) {
            throw new RangeException("End of range must be greater than the start:" + range);
        }
        return true;
    }

}
