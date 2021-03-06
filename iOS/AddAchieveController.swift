//
//  AddAchieveController.swift
//  seven
//
//  Created by Airing on 15/11/7.
//  Copyright © 2015年 Airing. All rights reserved.
//

import UIKit
import SwiftHTTP
import JSONJoy

class AddAchieveController: UIViewController, UITextFieldDelegate {
    
    struct ResponseData : JSONJoy {
        var result: Int?
        init(_ decoder: JSONDecoder) {
            result = decoder["result"].integer
        }
    }
    
    var stuId:Int?
    
    @IBOutlet weak var txtCourseNo: UITextField!
    @IBOutlet weak var txtStudentNo: UITextField!
    @IBOutlet weak var txtCourseAchieve: UITextField!
    
    
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
    
    @IBAction func addAchieve() {
        let request = HTTPTask()
        let params: Dictionary<String,AnyObject> = ["courseNo": txtCourseNo.text!, "studentNo": txtStudentNo.text!, "courseAchieve": txtCourseAchieve.text!]
        request.POST("http://localhost:8080/seven/add_achieve.action", parameters: params, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let data = ResponseData(JSONDecoder(res))
                print("result:\(data.result!)")
                if (data.result! == 1) {
                    print("addAchieve secceed")
                } else {
                    print("addAchieve fail")
                }
            }
        })
    }
    
    
}