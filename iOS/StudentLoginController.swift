//
//  StudentLoginController.swift
//  seven
//
//  Created by Airing on 15/11/6.
//  Copyright © 2015年 Airing. All rights reserved.
//


import UIKit
import SwiftHTTP
import JSONJoy

class StudentLoginController: UIViewController,UITextFieldDelegate {
    struct Student : JSONJoy {
        var result: Int?
        var studentId: Int?
        var studentNo: String?
        var studentPassword: String?
        var studentName: String?
        var studentSex: String?
        var studentAge: Int?
        init() {
        }
        init(_ decoder: JSONDecoder) {
            result = decoder["result"].integer
            studentId = decoder["studentId"].integer
            studentNo = decoder["studentNo"].string
            studentPassword = decoder["studentPassword"].string
            studentName = decoder["studentName"].string
            studentSex = decoder["studentSex"].string
            studentAge = decoder["studentAge"].integer
        }
    }
    var stuId:Int = 0
    
    @IBOutlet weak var txtStudentNo: UITextField!
    @IBOutlet weak var txtStudentPassword: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func login() {
        
        let request = HTTPTask()
        let params: Dictionary<String,AnyObject> = ["studentNo": txtStudentNo.text!, "studentPassword": txtStudentPassword.text!]
        request.POST("http://localhost:8080/seven/login.action", parameters: params, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let student = Student(JSONDecoder(res))
                print("result:\(student.result!),studentId:\(student.studentId!),studentNo:\(student.studentNo!),studentName:\(student.studentName!),studentSex:\(student.studentSex!),studentAge:\(student.studentAge!)")
                if (student.result! == 1) {
                    self.stuId = student.studentId!
                    print(self.stuId)
                    NSOperationQueue.mainQueue().addOperationWithBlock({ () -> Void in
                        
                        self.performSegueWithIdentifier("LoginSegue", sender: self.stuId)
                    })
                    
                } else {
                    print("login fail")
                }
            }
        })
    }
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "LoginSegue" {
            let controller = segue.destinationViewController as! StudentMainPageController
            //controller.itemString = sender as? String
            controller.stuId = self.stuId
            
        }
    }
    
}