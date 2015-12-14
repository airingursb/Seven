//
//  EditStudentController.swift
//  seven
//
//  Created by Airing on 15/11/7.
//  Copyright © 2015年 Airing. All rights reserved.
//

import UIKit
import SwiftHTTP
import JSONJoy

class EditStudentController: UIViewController,UITextFieldDelegate {
    struct JSON : JSONJoy {
        var result: Int?
        init() {
        }
        init(_ decoder: JSONDecoder) {
            result = decoder["result"].integer
        }
    }
    
    
    @IBOutlet weak var txtStudentNo: UITextField!
    @IBOutlet weak var txtStudentName: UITextField!
    @IBOutlet weak var txtStudentSex: UITextField!
    @IBOutlet weak var txtStudentAge: UITextField!
    @IBOutlet weak var txtStudentPassword: UITextField!
    
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
    
    @IBAction func editStudent() {
        let request = HTTPTask()
        let params: Dictionary<String,AnyObject> = ["studentNo": txtStudentNo.text!, "studentName": txtStudentName.text!, "studentSex": txtStudentSex.text!, "studentAge": txtStudentAge.text!,"studentPassword": txtStudentPassword.text!]
        request.POST("http://localhost:8080/seven/edit_student.action", parameters: params, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let json = JSON(JSONDecoder(res))
                print("result: \(json.result!)")
                if (json.result! == 1) {
                    print("edit student succeed!")
                } else {
                    print("edit student failed!")
                }
            }
        })
    }
    
    
}
