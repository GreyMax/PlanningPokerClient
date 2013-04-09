package planning.poker;

import java.io.Serializable;

public class Estimate implements Serializable {

    private String description;
    private Double optimistic;
    private Double pessimistic;
    private Double moreLikely;
    private Double weightedAvg;

    public Estimate(String description,
                    Double optimistic,
                    Double pessimistic,
                    Double moreLikely)
    {
        this.description = description;
        this.optimistic = optimistic;
        this.pessimistic = pessimistic;
        this.moreLikely = moreLikely;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Double optimistic) {
        this.optimistic = optimistic;
    }

    public Double getPessimistic() {
        return pessimistic;
    }

    public void setPessimistic(Double pessimistic) {
        this.pessimistic = pessimistic;
    }

    public Double getMoreLikely() {
        return moreLikely;
    }

    public void setMoreLikely(Double moreLikely) {
        this.moreLikely = moreLikely;
    }

    public Double getWeightedAvg() {
        return weightedAvg;
    }

    public void evaluateWeightedAvg() {
        this.weightedAvg = (optimistic + pessimistic + 4 * moreLikely)/6;
    }
}
