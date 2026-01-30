package ics202;

public class PrintingVisitor extends AbstractVisitor {

    @Override
    public void visit(Object obj) {
        System.out.println(obj);
    }
}
