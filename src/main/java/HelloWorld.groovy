import groovy.swing.SwingBuilder
import java.awt.BorderLayout
import java.awt.BorderLayout as BL

/**
 * Created by zhengshouzi on 2015/12/6.
 */
class HelloWorld {
    static void main(String[] args) {
        //输出语句
        println("hello groovy")
        //定义变量
        def var = "zhengqiang"
        println(var)
        //输出类型
        println(var.class)
        //定义不一样的变量
        def var1 = """hellos
                    world
                       groovy"""
        println(var1)
        //改变变量值改变类型
        var = 12334
        println(var.class)

        def var2 = "hello " + "zhengqiang" + ".groovy!"
        //函数调用
        repeate(var2)

        //使用结合
        def collect = ["a", "b", "c"]
        //添加
        collect.add(1);
        collect << "come on";
        collect[collect.size()] = 110.0
        //索引
        println(collect[collect.size() - 1])
        println(collect[4])
        //负索引、
        println(collect[-1])

        //集合运算
        collect = collect + 5; //添加元素
        collect = collect - "a" //减去"a"
        println collect[0]

        //两个结合之间的操作
        collect = collect - collect[0..2] //去掉前两个元素
        println collect[0]
        println collect[-1]

        //map操作，实际就是java里面的LinkedHashMap
        def map = ['name': 'zhengqiang', 'age': '24', 'sex': 'man']

        map = map + ['weight': 24]
        map.put('length', 189)
        map.father = 'Keller'

        //检索值
        println(map['father'])
        println map.length

        //闭包
        //闭包是用符号括起来的代码快，他可以单独运行与调用，也可以被命名。类似于匿名类或内联函数的概念
        //闭包中最常见的引用就是对集合进行迭代，

        map.each({ key, value -> println "$key:$value" })//key,value是两个参数用于接收每个元素的键值。

        map.each { println it }//it是一个关键字，代表map集合中的每个元素
        map.each({ println it.getKey() + " -->" + it.getValue() })

        //除了用于迭代至于闭包也可以单独定义

        def say = { word -> println "Hi ,$word" }

        say('groovy')
        //看起来，闭包类似于方法，需要定义参数和要执行的语句，它也可以通过名称被调用。
        // 然而闭包对象（不要奇怪，闭包也是对象）可以作为参数传递（比如前面的闭包作为参数传递给了map的each方法）。
        // 而在java中，要做到这一点并不容易（也许C++中的函数指针可以，但不要忘记java中没有指针）。
        // 其次，闭包也可以不命名（当然作为代价，只能在定义闭包时执行一次），而方法不可以。

        //类
        //方法默认public说明符
        //属性默认private
        //不需要getter/setter
        //不需要构造函数
        //不需要return
        //不需要(),方法调用是是可以省略括号的

        //使用后面定义的Person类
        def person1 = new Person()
        person1.name = 'zhengqiang'
        person1.age = 32
        println person1

        def person2 = new Person(['name': 'qq', 'age': 34])
        println person2

        // ?运算符,当前面的值不为空时，做后面的动作

        person1?.println 234

        //可变参数

        //枚举,见后面
        def today = Day.MONDAY
        switch (today) {
            case [Day.SATURDAY, Day.SUNDAY]:
                println "weekends are cool"
                break
            case Day.MONDAY..Day.FRIDAY:
                println "boring work day"
                break
            default:
                println " are you sure this is a valid day?"
        }
        //注意，switch和case中可以使用任何对象，
        // 尤其是可以在case中使用List和范围，
        // 从而使分支满足多个条件（这点跟delphi有点象）。
        Planet.EARTH.printMe()

        //  Elvis操作符
        def name
        //在groovy中，也可以简化为（因为null在groovy中可以转化为布尔值false）：
        def displayname = name ? name : "unKnow"
        //更加简化
        def displayname1 = name ?: "unKnow"

        //动态性
        def msg = "Hello"
        //通过元类，可以为这个对象增加方法
        println msg.metaClass
        String.metaClass.up = { delegate.toUpperCase() }

        //通过元类，我们还可以检索对象所拥有的方法和属性（就象反射）：
        // msg.metaClass.methods.each {println it.name}
        // msg.metaClass.properties.each {println it.name}

        //判断有没有up方法，然后调用
        if (msg.metaClass.respondsTo(msg, 'up')) {
            println msg.up()
        }
        if (msg.metaClass.hasProperty(msg, 'bytes')) {
            println msg.bytes.encodeBase64()
        }

        //Groovy Swing

        def swing = new SwingBuilder();
        def count = 0;
        def textlabel
        def frame = swing.frame(title: 'Frame', size: [300, 300]) {
            borderLayout()
            textlabel = label(text: 'Clicked ${count} time(s)', constraints: BL.NORTH)
            button(text: 'Click me', actionPerformed: {
                count++; textlabel.text = "Clicked ${count} time(s)."; println 'clicked'
            }, constraints: BorderLayout.SOUTH)
        }
        frame.pack()
        frame.show()

        //单元测试


    }
    /**
     * 方法定义，默认值，和特殊的循环，
     * @param var
     * @param repeat
     */
    static def repeate(var, repeat = 3) {
        for (int i = 0; i < repeat; i++) {
            println(var)
            println "This is ${i}:${var}"
        }
        for (i in 0..repeat) {
            println(var)
        }
    }
}

class Person {
    def name
    def age

    String toString() {
        //返回值是默认最后一行的值
        "$name,$age"
    }
}

enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7)
    double mass
    double radius

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    void printMe() {
        println "${name()} has a mass of ${mass}" + " and a radius of ${radius}"
    }
}