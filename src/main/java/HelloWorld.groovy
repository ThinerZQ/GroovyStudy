import groovy.swing.SwingBuilder
import java.awt.BorderLayout
import java.awt.BorderLayout as BL

/**
 * Created by zhengshouzi on 2015/12/6.
 */
class HelloWorld {
    static void main(String[] args) {
        //������
        println("hello groovy")
        //�������
        def var = "zhengqiang"
        println(var)
        //�������
        println(var.class)
        //���岻һ���ı���
        def var1 = """hellos
                    world
                       groovy"""
        println(var1)
        //�ı����ֵ�ı�����
        var = 12334
        println(var.class)

        def var2 = "hello " + "zhengqiang" + ".groovy!"
        //��������
        repeate(var2)

        //ʹ�ý��
        def collect = ["a", "b", "c"]
        //���
        collect.add(1);
        collect << "come on";
        collect[collect.size()] = 110.0
        //����
        println(collect[collect.size() - 1])
        println(collect[4])
        //��������
        println(collect[-1])

        //��������
        collect = collect + 5; //���Ԫ��
        collect = collect - "a" //��ȥ"a"
        println collect[0]

        //�������֮��Ĳ���
        collect = collect - collect[0..2] //ȥ��ǰ����Ԫ��
        println collect[0]
        println collect[-1]

        //map������ʵ�ʾ���java�����LinkedHashMap
        def map = ['name': 'zhengqiang', 'age': '24', 'sex': 'man']

        map = map + ['weight': 24]
        map.put('length', 189)
        map.father = 'Keller'

        //����ֵ
        println(map['father'])
        println map.length

        //�հ�
        //�հ����÷����������Ĵ���죬�����Ե�����������ã�Ҳ���Ա�����������������������������ĸ���
        //�հ�����������þ��ǶԼ��Ͻ��е�����

        map.each({ key, value -> println "$key:$value" })//key,value�������������ڽ���ÿ��Ԫ�صļ�ֵ��

        map.each { println it }//it��һ���ؼ��֣�����map�����е�ÿ��Ԫ��
        map.each({ println it.getKey() + " -->" + it.getValue() })

        //�������ڵ������ڱհ�Ҳ���Ե�������

        def say = { word -> println "Hi ,$word" }

        say('groovy')
        //���������հ������ڷ�������Ҫ���������Ҫִ�е���䣬��Ҳ����ͨ�����Ʊ����á�
        // Ȼ���հ����󣨲�Ҫ��֣��հ�Ҳ�Ƕ��󣩿�����Ϊ�������ݣ�����ǰ��ıհ���Ϊ�������ݸ���map��each��������
        // ����java�У�Ҫ������һ�㲢�����ף�Ҳ��C++�еĺ���ָ����ԣ�����Ҫ����java��û��ָ�룩��
        // ��Σ��հ�Ҳ���Բ���������Ȼ��Ϊ���ۣ�ֻ���ڶ���հ�ʱִ��һ�Σ��������������ԡ�

        //��
        //����Ĭ��public˵����
        //����Ĭ��private
        //����Ҫgetter/setter
        //����Ҫ���캯��
        //����Ҫreturn
        //����Ҫ(),�����������ǿ���ʡ�����ŵ�

        //ʹ�ú��涨���Person��
        def person1 = new Person()
        person1.name = 'zhengqiang'
        person1.age = 32
        println person1

        def person2 = new Person(['name': 'qq', 'age': 34])
        println person2

        // ?�����,��ǰ���ֵ��Ϊ��ʱ��������Ķ���

        person1?.println 234

        //�ɱ����

        //ö��,������
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
        //ע�⣬switch��case�п���ʹ���κζ���
        // �����ǿ�����case��ʹ��List�ͷ�Χ��
        // �Ӷ�ʹ��֧����������������delphi�е��󣩡�
        Planet.EARTH.printMe()

        //  Elvis������
        def name
        //��groovy�У�Ҳ���Լ�Ϊ����Ϊnull��groovy�п���ת��Ϊ����ֵfalse����
        def displayname = name ? name : "unKnow"
        //���Ӽ�
        def displayname1 = name ?: "unKnow"

        //��̬��
        def msg = "Hello"
        //ͨ��Ԫ�࣬����Ϊ����������ӷ���
        println msg.metaClass
        String.metaClass.up = { delegate.toUpperCase() }

        //ͨ��Ԫ�࣬���ǻ����Լ���������ӵ�еķ��������ԣ������䣩��
        // msg.metaClass.methods.each {println it.name}
        // msg.metaClass.properties.each {println it.name}

        //�ж���û��up������Ȼ�����
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

        //��Ԫ����


    }
    /**
     * �������壬Ĭ��ֵ���������ѭ����
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
        //����ֵ��Ĭ�����һ�е�ֵ
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