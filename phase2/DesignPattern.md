## Design Pattern

### Entity
    There is a folder under entity named event factory. Class in that folder are involved in the implementation of the abstract design pattern

        We use Abstract Factory Design Pattern for different types of events (i.e. `NoneSpeakerEvent`, `OneSpeakerEvent`, `MultiSpeakerEvent`)
        The reason we decide to create these three subclasses inherit from Event and make precise Event type like `Part`, `Discussion Panel`, 
        `Talk` be subclasses of these three class is that it will be much easier to extend if we want to add more precise Event types
        (e.g. selfStudyEvent inherit from NonSpeakerEvent). And using Abstract Fanctory easily help us create each type of event.

### Controller
    aaa

### Presenter
    aaa

### Gateway
    aaa