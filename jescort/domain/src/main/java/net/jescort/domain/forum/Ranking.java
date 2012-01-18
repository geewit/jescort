package net.jescort.domain.forum;

import java.util.Map;

import net.gelif.kernel.core.data.domain.AbstractPersistable;
import net.jescort.domain.enums.RankingStatus;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-2
 * Time: 上午11:46
 */

public class Ranking extends AbstractPersistable<Integer> implements Comparable<Ranking>
{
    private static final long serialVersionUID = 1L;

    public Ranking()
    {
    }

    private Integer id;
    private int minScore;
    private int maxScore;
    private String name;
    private String image;
    private Map<RankingStatus, Boolean> status;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public int getMinScore()
    {
        return minScore;
    }

    public void setMinScore(int minScore)
    {
        this.minScore = minScore;
    }

    public int getMaxScore()
    {
        return maxScore;
    }

    public void setMaxScore(int maxScore)
    {
        this.maxScore = maxScore;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public Map<RankingStatus, Boolean> getStatus()
    {
        return status;
    }

    public void setStatus(Map<RankingStatus, Boolean> status)
    {
        this.status = status;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Ranking))
        {
            return false;
        }
        final Ranking ranking = (Ranking) object;
        return new EqualsBuilder().append(id, ranking.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id).append("name", this.name).append("image", this.image).append("minScore", this.minScore).append("maxScore", this.maxScore).toString();
    }

    public int compareTo(Ranking ranking)
    {
        return new CompareToBuilder().append(getId(), ranking.getId()).toComparison();
    }
}
