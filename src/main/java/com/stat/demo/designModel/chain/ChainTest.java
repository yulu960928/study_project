package com.stat.demo.designModel.chain;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ChainTest {

    /**
     * 请假场景：根据天数选择不同的处理人。
     */
    public static void main(String[] args) {
        ChainProcessor chainProcessor = new ChainProcessor();
        Context context = new Context(100);
        chainProcessor.process(context);
        System.out.println(JSON.toJSONString(context));
    }
}

class ChainProcessor {
    public void process(Context context) {
        Handler leader = new Leader();
        Handler manager = new Manager();
        Handler boss = new Boss();
        leader.setNextHandler(manager);
        manager.setNextHandler(boss);

        leader.doProcess(context);
    }
}

abstract class Handler {

    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    abstract void doProcess(Context context);

    public String getName() {
        return getClass().getSimpleName();
    }
}

class Leader extends Handler {

    @Override
    void doProcess(Context context) {
        context.addProcessor(getName());
        if (context.getDays() <= 10) {
            context.setApprover(getName());
        } else {
            nextHandler.doProcess(context);
        }
    }
}

class Manager extends Handler {

    @Override
    void doProcess(Context context) {
        context.addProcessor(getName());
        if (context.getDays() > 10 && context.getDays() <= 20) {
            context.setApprover(getName());
        } else {
            nextHandler.doProcess(context);
        }
    }
}

class Boss extends Handler {

    @Override
    void doProcess(Context context) {
        context.addProcessor(getName());
        if (context.getDays() > 20 && context.getDays() <= 100) {
            context.setApprover(getName());
        } else {
            nextHandler.doProcess(context);
        }
    }
}


@Data
@AllArgsConstructor
class Context {
    private int days;
    private List<String> processors;
    private String approver;

    public Context(int days) {
        this.days = days;
    }

    public void addProcessor(String processor) {
        if (CollectionUtils.isEmpty(processors)) {
            processors = new ArrayList<>();
        }
        processors.add(processor);
    }
}