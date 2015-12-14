//
//  DeleteTeacherController.swift
//  seven
//
//  Created by Airing on 15/11/7.
//  Copyright © 2015年 Airing. All rights reserved.
//

import UIKit
import SwiftHTTP
import JSONJoy

class DeleteTeacherController: UIViewController, UITextFieldDelegate {
    struct JSON : JSONJoy {
        var result: Int?
        init() {
        }
        init(_ decoder: JSONDecoder) {
            result = decoder["result"].integer
        }
    }

    @IBOutlet weak var txtTeacherNo: UITextField!
    
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
    
    @IBAction func deleteTeacher() {
        let request = HTTPTask()
        let params: Dictionary<String,AnyObject> = ["teacherNo": txtTeacherNo.text!]
        request.POST("http://localhost:8080/seven/delete_teacher.action", parameters: params, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let json = JSON(JSONDecoder(res))
                print("result: \(json.result!)")
                if (json.result! == 1) {
                    print("delete teacher succeed!")
                } else {
                    print("delete teacher failed!")
                }
            }
        })
    }
    
    
}