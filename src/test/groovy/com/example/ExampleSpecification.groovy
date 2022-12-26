package com.example

import org.example.Color
import org.example.Palette
import org.example.Polygon
import org.example.Renderer
import org.example.ShapeFactory
import org.example.TooFewSidesException
import spock.lang.Specification
import spock.lang.Subject

class ExampleSpecification extends Specification {

    //test with spock can be strings, we allows to be very specific about what it is testing
    def "should be a simple assertion"() {
        expect: //spock allows to define the tests using labels,and it is not necessary to use assert
        1 == 1
    }

    def "should demonstrate given-when-then"() {
        given: //sets the condition for the test
        def polygon = new Polygon(4)

        when:
        int sides = polygon.numberOfSides //if this field doesn't have a getter, groovy still will be able to run it,
                                          //also observe that it is possible to call it without the "get" in front

        then: //checking that the conditions of the test are being made
        sides == 4
    }

    def "should demonstrate when-then"() {  //we can also omit the given al simplify the code
        when:
        int sides = new Polygon(4).numberOfSides //if this field doesn't have a getter, groovy still will be able to run it,
                                                              //also observe that it is possible to call it without the "get" in front

        then: //checking that the conditions of the test are being made
        sides == 4
    }

    def "should expect Exceptions"() {
        when:
        new Polygon(0)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == 0
    }

    def "should expect Exception for a number of invalid input: #sides"() {
        when:
        new Polygon(sides)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == sides

        where: //specifies the input values for the test
               //left shift operator in here inputs the pipeline of values to be used in the test
        sides << [-1, 0, 1, 2, 3] //3 should fail the test
    }

    def "should be able to create a polygon with #sides sides"() {
        expect: //when there is only one statement to be tested it is possible to use the label expect
        new Polygon(sides).numberOfSides == sides

        where:
        sides << [3, 4, 5, 1, 8, 18] //1 should fail
    }

    def "should use data tables for calculating max. Max of #a and #b is #max"() {
        expect:
        Math.max(a, b) == max

        where:
        a | b | max
        1 | 3 | 3
        7 | 4 | 7
        6 | 6 | 6
    }

    def "should be able to mock a concrete class"() {
        given:
        Renderer renderer = Mock()
        @Subject //mocks the object to being tested
        def polygon = new Polygon(sides, renderer)

        when:
        polygon.draw()

        then:
        sides * renderer.drawLine()

        where:
        sides << [1, 3, 5] //1 should fail
    }

    def "should be able to create a stub"() {
        given:
        Palette palette = Stub()
        palette.getPrimaryColor() >> Color.RED // stating that when getPrimaryColor() is called, Color.RED is returned
        @Subject
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegoundColor() == Color.RED
    }

    def "should use with"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        with(polygon) {//using with allows to access the properties without the get
            numberOfSides == 4 //using anything other than 4 here fail, without testing the renderer part
            renderer == renderer
        }
    }

    def "should use verifyAll"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        verifyAll(polygon) {//using with allows to access the properties without the get
            numberOfSides == 5
            renderer == null //this also fails, even when the previous failed too
        }
    }

}
