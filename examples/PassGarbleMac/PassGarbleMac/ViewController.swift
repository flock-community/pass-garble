//
//  ViewController.swift
//  PassGarbleMac
//
//  Created by Julius van Dis on 19/02/2022.
//

import Cocoa
import PassGarbleMacOS

class ViewController: NSViewController {
    let passwordGenerator = PassGarble()
    
    
    
    @IBOutlet weak var generatedPasswordField: NSTextField!

    @IBOutlet weak var passwordLength: NSTextField!
    @IBOutlet weak var includeLowercase: NSButton!
    @IBOutlet weak var includeUpperCase: NSButton!
    @IBOutlet weak var includeNumbers: NSButton!
    @IBOutlet weak var includeSpecialCharacters: NSButton!
    

    
    override func viewDidLoad() {
        super.viewDidLoad()
        let defaultPasswordOptions = passwordGenerator.defaultOptions()
        // Do any additional setup after loading the view.
        passwordLength.intValue = defaultPasswordOptions.passwordLength
        includeLowercase.state = boolToStateValue(defaultPasswordOptions.includeLowerCase)
        includeUpperCase.state = defaultPasswordOptions.includeUpperCase ? NSControl.StateValue.on :  NSControl.StateValue.off
        includeNumbers.state = defaultPasswordOptions.includeNumbers ? NSControl.StateValue.on :  NSControl.StateValue.off
        includeSpecialCharacters.state = defaultPasswordOptions.includeSpecialChars ? NSControl.StateValue.on :  NSControl.StateValue.off
    }

    override var representedObject: Any? {
        didSet {
        // Update the view, if already loaded.
        }
    }


    @IBAction func generatePassword(_ sender: Any) async {
        
        let x = CommonPasswordGenerationOptions(passwordLength: passwordLength.intValue,
                                                includeLowerCase: checked(includeLowercase),
                                                includeUpperCase: checked(includeUpperCase),
                                                includeNumbers: checked(includeNumbers),
                                                includeSpecialChars: checked(includeSpecialCharacters),
                                                specialCharSet: passwordGenerator.defaultSpecialChars())
        do {
            generatedPasswordField.stringValue = try await passwordGenerator.generate(generationOptions: x)
        } catch {
            // Couldn't create audio player object, log the error
            print("Couldn't generate a password")
        }
    }
    
    func checked(_ checkbox: NSButton) -> Bool{
        return checkbox.state == NSControl.StateValue.on
    }
    
    func boolToStateValue(_ booleanValue: Bool) -> NSControl.StateValue{
        return booleanValue ? NSControl.StateValue.on :  NSControl.StateValue.off
    }
}

