//
//  AdminLoginController.swift
//  seven
//
//  Created by Airing on 15/11/6.
//  Copyright © 2015年 Airing. All rights reserved.
//


import UIKit

class AdminLoginController: UIViewController,UITextFieldDelegate {
    
    @IBOutlet weak var txtAccount: UITextField!
    @IBOutlet weak var txtPassword: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func adminLogin() {
        if (txtAccount.text == "admin" && txtPassword.text == "admin"){
            self.performSegueWithIdentifier("AdminLoginSegue", sender: nil)
        }else{
            print("login fail")
        }
        
    }
    
}