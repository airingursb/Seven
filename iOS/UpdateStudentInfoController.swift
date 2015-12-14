//
//  UpdateStudentInfoController.swift
//  seven
//
//  Created by Airing on 15/11/6.
//  Copyright © 2015年 Airing. All rights reserved.
//

import UIKit
import SwiftHTTP
import JSONJoy

class UpdateStudentInfoController: UIViewController, UITextFieldDelegate {
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
    var stuId:Int?
    
    @IBOutlet weak var txtStudentPassword: UITextField!
    @IBOutlet weak var txtStudentName: UITextField!
    @IBOutlet weak var txtStudentSex: UITextField!
    @IBOutlet weak var txtStudentAge: UITextField!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let request = HTTPTask()
        let params: Dictionary<String,AnyObject> = ["studentId": stuId!]
        request.POST("http://localhost:8080/seven/show_student.action", parameters: params, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let student = Student(JSONDecoder(res))
                print("result:\(student.result!),studentId:\(student.studentId!),studentNo:\(student.studentNo!),studentName:\(student.studentName!),studentSex:\(student.studentSex!),studentAge:\(student.studentAge!),studentPassword:\(student.studentPassword!)")
                if (student.result! == 1) {
                    NSOperationQueue.mainQueue().addOperationWithBlock({ () -> Void in
                        
                        self.txtStudentPassword.text = student.studentPassword!
                        self.txtStudentName.text = student.studentName!
                        self.txtStudentSex.text = student.studentSex!
                        self.txtStudentAge.text = String(student.studentAge!)
                    })
                } else {
                    print("show fail")
                }
            }
        })
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func updateStudent() {
        let request = HTTPTask()
        let params: Dictionary<String,AnyObject> = ["studentId": stuId!,"studentPassword": txtStudentPassword.text!,"studentName": txtStudentName.text!,"studentSex": txtStudentSex.text!,"studentAge": txtStudentAge.text!]
        request.POST("http://localhost:8080/seven/update_student.action", parameters: params, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let student = Student(JSONDecoder(res))
                print("result:\(student.result!)")
                if (student.result! == 1) {
                    print("update secceed")
                } else {
                    print("update fail")
                }
            }
        })
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
}