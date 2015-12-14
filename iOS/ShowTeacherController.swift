//
//  ShowTeacherController.swift
//  seven
//
//  Created by Airing on 15/11/7.
//  Copyright © 2015年 Airing. All rights reserved.
//

import UIKit
import SwiftHTTP
import JSONJoy

class ShowTeacherController: UIViewController {
    struct JSON : JSONJoy {
        var result: Int?
        var numberOfJob1: Int?
        var numberOfJob2: Int?
        var numberOfJob3: Int?
        var salaryOfJob1: Double?
        var salaryOfJob2: Double?
        var salaryOfJob3: Double?
        init() {
        }
        init(_ decoder: JSONDecoder) {
            result = decoder["result"].integer
            numberOfJob1 = decoder["numberOfJob1"].integer
            numberOfJob2 = decoder["numberOfJob2"].integer
            numberOfJob3 = decoder["numberOfJob3"].integer
            salaryOfJob1 = decoder["salaryOfJob1"].double
            salaryOfJob2 = decoder["salaryOfJob2"].double
            salaryOfJob3 = decoder["salaryOfJob3"].double
        }
    }
    
    @IBOutlet weak var lblNumOfJob1: UILabel!
    @IBOutlet weak var lblNumOfJob2: UILabel!
    @IBOutlet weak var lblNumOfJob3: UILabel!
    
    @IBOutlet weak var lblSalaryOfJob1: UILabel!
    @IBOutlet weak var lblSalaryOfJob2: UILabel!
    @IBOutlet weak var lblSalaryOfJob3: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func showTeacherJob() {
        let request = HTTPTask()
        request.POST("http://localhost:8080/seven/show_teacher_job.action", parameters: nil, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let json = JSON(JSONDecoder(res))
                print("result: \(json.result!)")
                if (json.result! == 1) {
                    print("show teacherJob succeed!")
                    NSOperationQueue.mainQueue().addOperationWithBlock({ () -> Void in
                        
                        self.lblNumOfJob1.text = String(json.numberOfJob1!)
                        self.lblNumOfJob2.text = String(json.numberOfJob2!)
                        self.lblNumOfJob3.text = String(json.numberOfJob3!)
                        self.lblSalaryOfJob1.text = String(json.salaryOfJob1!)
                        self.lblSalaryOfJob2.text = String(json.salaryOfJob2!)
                        self.lblSalaryOfJob3.text = String(json.salaryOfJob3!)
                })
                } else {
                    print("show teacherJob failed!")
                }
            }
        })
    }
    
    
}