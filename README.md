# Snake

Simple snake game written in **Kotlin**.
Why you should choose it instead of another Snake game? I don't see any reason. Or maybe one fun thing - you can create your own maps. 

## How it looks?

![screenshot 2017-08-02](https://user-images.githubusercontent.com/18220458/28890413-52e6437e-77c7-11e7-8478-8006450f209c.png)
<br/><br/><br/>

![screenshot 2017-07-30](https://user-images.githubusercontent.com/18220458/28889943-e1982256-77c5-11e7-8600-92e2aecea7bb.png)
<br/><br/><br/>

![screenshot 2017-08-02 1](https://user-images.githubusercontent.com/18220458/28890412-52e5455a-77c7-11e7-8e00-d70063d942ec.png)
<br/><br/><br/>

![screenshot 2017-08-02 2](https://user-images.githubusercontent.com/18220458/28890414-52e8d472-77c7-11e7-9148-8c49cacb299f.png)
<br/><br/><br/>

## How to add custom map?
Firstly, you must to create `text file` and fill it with 1 and 0, where 1 means "brick" and 0 means "nothing". For example:

````
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1
1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1
1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
````

Then, you have to save it with `.sn` extension (e.g. `mymap.sn`). Lastly, you must choose your file in game settings. 

![settings](https://user-images.githubusercontent.com/18220458/28898896-7f091546-77e8-11e7-9076-d18a98d3fa4b.png)

Voilà. You can now play in your very own map!

<br/><br/>

## How to get?
If you want to play in this super original and innovative game you can download the zip with [the latest release](https://github.com/grudus/Snake/releases). <br/>

Or if you want to contribute it itself 

````bash
git clone https://github.com/grudus/Snake.git
cd Snake
gradle fatJar
cd ./build/libs
java -jar Snake-all-{version}.jar
````

**Remember** that *config* file must be in the same directory as actual game!

<br/>

## How to play?

**UP/W DOWN/S RIGHT/D LEFT/A** to move Snake.

**Space** to temporarily change speed to `FAST`

**ESC** to pause

<br/>

## Dev info

Application is written in [Kotlin programming language](https://kotlinlang.org/) and uses [Swing](https://en.wikipedia.org/wiki/Swing_(Java)) for graphic interface (GUI)

Additionaly, it uses:
* [Gradle](https://gradle.org/) for build an application
* [Jackson for kotlin](https://github.com/FasterXML/jackson-module-kotlin) for parsing JSON files
* [SLF4J](https://www.slf4j.org/) for logging messages
* [RxKotlin](https://github.com/ReactiveX/RxKotlin) for simple implementation of EventBus

Source of sprites and icons:
* [Snake](https://github.com/eugeneloza/SnakeGame)
* Brick and foods - Icons from www.flaticon.com made by Freepik and Madebyoliver 

<br/><br/><br/>
Feel free to contribute.
