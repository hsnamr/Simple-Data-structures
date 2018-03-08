package ics202;

public abstract class AbstractPrePostVisitor implements PrePostVisitor
{

    public void preVisit(Object obj)
    {
    }

    public void inVisit(Object obj)
    {
    }

    public void postVisit(Object obj)
    {
    }

    public boolean isDone()
    {
        return false;
    }
}